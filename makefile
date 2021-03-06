JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	*.java
	
default: clean classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) */*.class