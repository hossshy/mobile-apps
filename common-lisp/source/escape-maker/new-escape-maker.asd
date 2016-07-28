;;; -*- mode: lisp -*-


(defsystem :new-escape-maker
    :author "Kazamai, Kou"
    :licence "Public Domain"
    :serial t
    :components ((:file "package")
                 (:file "define")
                 (:file "base")
                 (:file "class")
                 (:file "escape-maker"))
    :depends-on (png split-sequence))
