(in-package :new-escape-maker)

(defparameter *project* nil)
(defparameter *stories* nil)
(defparameter *id-datas* (make-hash-table :test #'equal))

(defparameter *operation-word*
  '("if" "isset" "else" "end" "set" "msg" "go"
    "item" "delitem" "detail" "select" "additemonly"
    "show" "hide" "detailnoitem" "vibrate" "se" "toggle" "title" "story")
  "ミニ言語のキーワード")

(defparameter *operation-next-ignore-word*
  '("msg" "title" "ending" "event" "vibrate" "se" "toggle" "story" "talk")
  "次の単語がフリーテキストとなるキーワード")


(defun id->image-file-path (id)
  "指定IDの画像パスを返す"
  (if *project*
      (format nil "~a~a~a~d~a"
              +wtk-home+
              *project*
              +resource-dir+
              id
              +image-suffix+)
      (error "unknown project.")))

(defun object-image-size (id)
  "指定ID画像サイズを返す"
  (assert (> id 0))
  (multiple-value-list (image-size (id->image-file-path id))))

(defun add-id-data (data)
  (if (gethash (name data) *id-datas*)
      (error "already stored data: ~a" (name data))
      (setf (gethash (name data) *id-datas*) data)))


(define-condition data-not-found (error)
  ((name :initarg :name :reader name)))

(defun get-id-data (name)
  (let ((obj (gethash name *id-datas*)))
    (unless obj
      (error 'data-not-found :name name))
    obj))

(defun get-id (name)
  (id (get-id-data name)))


(defgeneric parse (key value)
  (:documentation "設定ファイルの個々の要素を解析しオブジェクトを生成する"))

(defmethod parse ((key (eql ':project)) value)
  (setf *project* value))

(defmethod parse ((key (eql ':stories)) value)
  (loop for program in value do
       (vector-push-extend
        (make-obj story) *stories*)))

(defmethod parse ((key (eql ':rooms)) value)
  (loop
     for v in value
     for id from 0
     do (destructuring-bind (name &key up down left right) v
          (push-new-id-data room-data))))

(defmethod parse ((key (eql ':flags)) value)
  (loop
     for name in value
     for id downfrom -100
     do (push-new-id-data flag)))

(defun item-image-source-point (id)
  (multiple-value-bind (a b)
      (truncate id (/ +max-image-width+ +item-image-width+))
    (list (* +item-image-width+ b) (* +item-image-width+ a))))

(defun apply-list (lst)
  (if (listp lst)
      (eval lst)
      lst))

(defun position-list (x y)
  (if (and x y)
      (list (apply-list x) (apply-list y))
      '(0 0)))


(defparameter *object-id* 0)

(defun parse-object (room-id objects)
  (loop
     for v in objects
     do (destructuring-bind (name program &key image-id
                                  dx dy sx sy width height
                                  visible root-id) v
          (let ((dest-point (position-list dx dy))
                (source-point (position-list sx sy))
                (size (if (and width height)
                          (list width height)
                          (object-image-size image-id)))
                (visible (if (string-equal visible "hide") nil 'show))
                (id (incf *object-id*)))
            (push-new-id-data object)))))

(defmethod parse ((key (eql ':objects)) value)
  (setf *object-id* 0)
  (loop 
     for room in value
     do (destructuring-bind (room-name objects) room
          (parse-object (get-id room-name) objects))))

(defmethod parse ((key (eql ':groups)) value)
  (loop 
     for v in value
     do (destructuring-bind (name &key image-id
                                  child-length
                                  dx dy
                                  width height
                                  show-index
                                  root-id room-id program) v
          (let ((id (incf *object-id*))
                (dest-point (position-list dx dy))
                (size (list width height))
                (room-id (get-id room-id)))
            (push-new-id-data group-data)
            (incf *object-id*  child-length)))))

(defmethod parse ((key (eql ':items)) value)
  (loop 
     for v in value
     for i from 0
     do (destructuring-bind (name detail-image-id detail-item-program
                                  &key item-program root-id source-point) v
          (let* ((id (incf *object-id*))
                 (detail-id (+ id 500)))
            (setf source-point (item-image-source-point i))
            (push-new-id-data item)))))

(defmethod parse ((key (eql ':specials)) value)
  (loop 
     for v in value
     do (destructuring-bind (name image-id &key program
                                  sx sy dx dy width height) v
          (let ((id (incf *object-id*))
                (dest-point (position-list dx dy))
                (source-point (position-list sx sy))
                (size (list width height)))
            (push-new-id-data special-data)))))


(defun get-group-id (word)
  (let* ((name (subseq word 1 (1- (length word))))
         (child-id (parse-integer word
                                  :start (1- (length word))
                                  :end (length word)))
         (group-data (get-id-data name)))
    (assert (< child-id (child-length group-data)))
    (+ (id group-data) child-id)))

(defun get-id-or-group-id (word)
  (if (char= #\g (aref word 0))
      (get-group-id word)
      (get-id word)))

(defun next-ignore-word-p (word)
  (find word *operation-next-ignore-word* :test #'equal))

(defun operation-word-p (word)
  (find word *operation-word* :test #'equal))

(defun parse-code (code-str)
  "ミニ言語の名前指定箇所をIDに変換して返す"
  (let ((ignore-flag))
    (flet
        ((code-name->id (word)
           (cond (ignore-flag
                  (setf ignore-flag nil) word)
                 ((next-ignore-word-p word)
                  (setf ignore-flag t) word)
                 ((operation-word-p word)
                  word)
                 (t (get-id-or-group-id word)))))
      (format nil "~{~a~^ ~}"
              (mapcar #'code-name->id
                      (split-sequence #\Space code-str :remove-empty-subseqs t))))))


(defgeneric update-id-data (key data)
  (:documentation "名前で紐付けている属性値をIDに変換する"))

(defmethod update-id-data (key (data room-data))
  (update-slots data #'get-id (up down left right) :default -1))

(defmethod update-id-data (key (data flag)) ())

(defmethod update-id-data (key (data object))
  (update-slots data #'parse-code (program) :default "")
  (update-slots data #'get-id (root-id) :default -1))

(defmethod update-id-data (key (data group-data))
  (update-slots data #'get-id (root-id) :default -1)
  (update-slots data #'parse-code (program) :default "")
  (or (show-index data)
      (setf (show-index data) -1)))

(defmethod update-id-data (key (data item))
  (update-slots data #'parse-code (detail-item-program) :default "")
  (with-slots (id item-program detail-id name) data
    (update-slots data #'parse-code (item-program)
                  :default (format nil "set select ~a set detail ~a set msg ~aだ…"
                                   id detail-id name)))
                                        ;root-idは詳細アイテムIDに紐付く
  (update-slots data #'get-id (root-id) :default -1))

(defmethod update-id-data (key (data special-data))
  (update-slots data #'parse-code (program) :default ""))


(defmethod write-story-source-code ((data story))
  (format *output-stream* "~a~%" (program data)))


(defparameter *now-key* nil)

(defgeneric write-source-code (name data)
  (:documentation "各オブジェクトをJavaソースに落とす"))

(defmethod write-source-code :before (name data)
  (unless (equal *now-key* (class-name (class-of data)))
    (when *now-key*
      (format *output-stream* ":~a~%~%" *now-key*))
    (setf *now-key* (class-name (class-of data)))
    (format *output-stream* ":~a~%" *now-key*)))

(defmethod write-source-code (name (data room-data))
  (with-slots (id up down left right name) data
    (declare (ignore name))
    (format *output-stream* 
            "~a,~a:~a:~a:~a~%"
            id up down left right)))

(defmethod write-source-code (name (data flag))
  (with-slots (id name) data
    (declare (ignore name))
    (format *output-stream* 
            "~a~%" id)))

(defmethod write-source-code (name (data item))
  (with-slots (id name detail-id detail-image-id detail-item-program
                  item-program source-point root-id) data
    ;; アイテム
    (format *output-stream* 
            "~a,~d,~d,~d,~d,~d,~a,~a,~a~%"
            id +item-image-id+ (first source-point) (second source-point)
            +item-image-width+ +item-image-width+ detail-id item-program root-id)
    ;; 詳細アイテム
    (format *output-stream*
            "~a,~d,~d,~d,~d,~d,~a,~a,~a~%"
            detail-id detail-image-id 0 0
            +item-detail-image-width+ +item-detail-image-width+
            0 detail-item-program root-id)))

(defmethod write-source-code (name (data object))
  (with-slots (id name program image-id dest-point source-point
                  size visible root-id room-id) data
    (declare (ignore name))
    (format *output-stream* 
            "~a,~d,~d,~d,~d,~d,~d,~d,~d,~a,~d,~a~%"
            id image-id room-id (first dest-point) (second dest-point)
            (first source-point) (second source-point)
            (first size) (second size)
            program visible root-id)))

(defmethod write-source-code (name (data group-data))
  (with-slots (id name image-id child-length dest-point
                  source-point size show-index root-id room-id program) data
    (loop
       for i below child-length
       do
         (format *output-stream* "~a,~d,~d,~d,~d,~d,~d,~d,~d,~a,~d,~a~a"
                 (+ id i) image-id room-id (first dest-point) (second dest-point)
                 (* i (first size)) 0
                 (first size) (second size)
                 program
                 (= i show-index) root-id #\Tab))
    (format *output-stream* "~%")))

(defmethod write-source-code (name (data special-data))
  (with-slots (id image-id name program dest-point source-point size) data
    (declare (ignore name))
    (format *output-stream* 
            "~a,~d,~d,~d,~d,~d,~d,~d,~a~%"
            id image-id (first dest-point) (second dest-point)
            (first source-point) (second source-point)
            (first size) (second size)
            program)))


(defun copy-text-file (filename stream)
  (with-open-file (in filename)
    (loop for l = (read-line in nil nil)
       while l do (write-line l stream))))


(defun output-source-file (filename)
  (with-open-file (out filename :direction :output :if-exists :supersede :external-format :shift_jis)
    (let ((*output-stream* out))
      (setf *now-key* nil)
      (format *output-stream* ":STORY~%")
      (map 'vector #'write-story-source-code *stories*)
      (format *output-stream* ":STORY~%~%")
      (maphash #'write-source-code *id-datas*)
      (format *output-stream* ":ITEM~%")
      )))


(defun load-file (filename)
  (with-open-file (in filename)
    (with-standard-io-syntax
      (read in))))

(defun make (file-name)
  (setf *stories* (make-array 10 :adjustable t :fill-pointer 0))
  (clrhash *id-datas*)
  (let ((data (load-file file-name)))
    (loop for (key value) in data do
         (format t "parse key :~a~%" key)
         (parse key value))
    (maphash #'update-id-data *id-datas*)
    (output-source-file
     ;(format nil "~a~a~a~a" +wtk-home+ *project* +main-src-dir+ +out-file-name+)
     +out-file-name+)))

