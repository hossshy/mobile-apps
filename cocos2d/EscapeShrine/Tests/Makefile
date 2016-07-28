CC = g++
CPPFLAGS = -std=c++11 -lcppunit

#SOURCES  = $(wildcard ../Classes/*.cpp)
#OBJS  = $(addprefix ./, $(SOURCES:.cpp=.o))
OBJS = ../Classes/Interpreter.o ../Classes/StrUtil.o UnitTest.o

all: target

target: $(OBJS)
	$(CC) $(CPPFLAGS) -std=c++11 -o UnitTest $(OBJS)

clean:
	rm -f ../Classes/*.o *.o UnitTest
