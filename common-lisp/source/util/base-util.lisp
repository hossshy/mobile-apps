(in-package :com.strnet.util)

(defun symbol-length (s)
  (length (symbol-name s)))

(defun symbol-first-match (sym prefix)
  (and (symbolp sym)
	   (> (symbol-length sym) (length prefix))
	   (kmrcl:string-starts-with prefix (symbol-name sym))))

(defun slot-names (object)
  (mapcar #'closer-mop:slot-definition-name
		  (closer-mop:class-slots (class-of object))))

(defun keyword-pair (arg)
  `(,(alexandria:make-keyword arg) ,arg))

(defmacro make-instance* (class-name &rest args)
  `(make-instance ,class-name ,@(mapcan #'keyword-pair args)))

;;; from "On Lisp"

(defun group (source n)
  (if (zerop n) (error "zero length"))
  (labels ((rec (source acc)
			 (let ((rest (nthcdr n source)))
			   (if (consp rest)
				   (rec rest (cons
							  (subseq source 0 n)
							  acc))
				   (nreverse (cons source acc))))))
	(if source (rec source nil) nil)))

;;; end from "on lisp"

;;; from "let over lambda"

(defun g!-symbol-p (s)
  (symbol-first-match s "G!"))

(defun o!-symbol-p (s)
  (symbol-first-match s "O!"))
  
(defmacro defmacro/g! (name args &body body)
  (let ((syms (remove-duplicates
			   (remove-if-not #'g!-symbol-p (alexandria:flatten body)))))
	`(defmacro ,name ,args
	   (let ,(mapcar
			  (lambda (s)
				`(,s (gensym ,(subseq (symbol-name s) 2))))
			  syms)
		 ,@body))))

(defun o!-symbol-to-g!-symbol (s)
  (kmrcl:concat-symbol "G!" (subseq (symbol-name s) 2)))

(defmacro defmacro! (name args &body body)
  (let* ((os (remove-if-not #'o!-symbol-p args))
		 (gs (mapcar #'o!-symbol-to-g!-symbol os)))
	`(defmacro/g! ,name ,args
	   `(let ,(mapcar #'list (list ,@gs) (list ,@os))
		  ,(progn ,@body)))))

(defun read-delimited-string (endchar stream)
  (do ((c (read-char stream) (read-char stream))
	   (ret))
	   ((char= c endchar) (coerce (nreverse ret) 'string))
	(push c ret)))

(defun concat (&rest args)
  (format nil "~{~a~}" args))
