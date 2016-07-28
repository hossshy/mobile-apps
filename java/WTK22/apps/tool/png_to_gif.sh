#!/bin/bash

cd $1
count=$2
while [ $count -ne -1 ]; do
	convert $count.png $count.gif
	rm -f $count.png
	count=`expr $count - 1`
done
convert str.png str.gif
rm -f str.png