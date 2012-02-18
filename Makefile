FUENTE = Main

all: compilar


compilar: 
	javac practicaIA/$(FUENTE).java
	
run:
	java practicaIA/Main


clean:
	rm practicaIA/*.class
