(defpackage :new-escape-maker
  (:use :cl :split-sequence :png)
  (:export :+wtk-home+
		   :slot->args
		   :make))

(in-package :new-escape-maker)


(defun as-keyword (sym)
  (intern (string sym) :keyword))

(defun slot->keyword-arg (namevalue)
  `(,(as-keyword namevalue) ,namevalue))

(defun slot-names (type)
  "指定クラスのスロット名を返す"
  (make-instance type) ; call finalize-inheritance
  (mapcar #'sb-mop:slot-definition-name
          (sb-mop:class-slots (find-class type))))

(defun slot->args (type)
  (mapcan #'slot->keyword-arg (slot-names type)))

