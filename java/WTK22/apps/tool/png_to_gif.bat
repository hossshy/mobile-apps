@echo off
cd %1
for /l %%A in (0,1,%2) do convert %%A.png %%A.gif
for /l %%A in (0,1,%2) do del %%A.png
convert str.png str.gif
del str.png