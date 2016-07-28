(in-package :com.strnet.util)

(defmacro! nif (o!expr pos zero neg)
  `(cond ((plusp ,g!expr) ,pos)
		 ((zerop ,g!expr) ,zero)
		 (t ,neg)))
