(in-package :new-escape-maker)


(defmacro make-obj (type)
  "各スロットに同名の変数値を入れインスタンスを作成する"
  `(make-instance ',type ,@(slot->args type)))

(defmacro push-new-id-data (type)
  "指定インスタンスを、スロット同名変数を用いて作成する"
  `(add-id-data (make-obj ,type)))


(defmacro update-slot (obj fn slot &key default)
  "指定インスタンスのスロットを引数に関数を呼び、その値をスロットに格納する"
  `(if (,slot ,obj)
       (setf (,slot ,obj) (funcall ,fn (,slot ,obj)))
       (setf (,slot ,obj) ,default)))

(defmacro update-slots (obj fn slots &key default)
  "指定インスタンスのスロット群を指定した関数で更新する"
  `(progn
     ,@(loop for slot in slots collect
            `(update-slot ,obj ,fn ,slot :default ,default))))

