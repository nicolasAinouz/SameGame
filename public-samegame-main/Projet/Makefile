JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	EventGame.java \
	RemplirCase.java \
	Grille.java \
	MenuFin.java \
	FenetreFin.java \
	Menu.java \
	Fenetre.java \
	Main.java

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(J) Main

clean:
	$(RM) *.class
