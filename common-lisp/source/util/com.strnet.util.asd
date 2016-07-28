;;; -*- mode: lisp -*-

(defsystem com.strnet.util
    :author "Kazamai, Kou"
	:serial t
    :components ((:file "packages")
				 (:file "base-util")
				 (:file "util"))
	:depends-on (:alexandria :kmrcl :closer-mop))
