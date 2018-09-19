ROOT_DIR := $(ROOTDIR)
PROJECT_ROOT := $(PROJECTROOT)
PROJECT_OUT := $(PROJECTOUT)
OUT_DIR := $(OUTDIR)

JAVA_FILES=$(shell find $(PROJECT_ROOT) -name "*.java")

.PHONY: all clean

all: $(JAVA_FILES)
	if [ ! -d $(PROJECT_OUT) ] ; then mkdir -p $(PROJECT_OUT) ; fi
	javac -d $(PROJECT_OUT) $^

clean:
	if [ -d "$(PROJECT_OUT)" ] ; then rm -r $(PROJECT_OUT) ; fi
