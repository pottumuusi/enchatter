ROOT_DIR := $(ROOTDIR)
PROJECT_ROOT := $(PROJECTROOT)
PROJECT_OUT := $(PROJECTOUT)
OUT_DIR := $(OUTDIR)

APPS := enchatter
APP_DIR_ENCHATTER := $(PROJECT_ROOT)/enchatter

.PHONY: all clean clean-enchatter $(APPS)

# Should put this in a makefile which can be included
define subproject_make_with_common_attributes
	$(eval SUBPROJECT_NAME=$1)
	$(eval SUBPROJECT_PATH=$2)
	$(eval SUBPROJECT_TARGET=$3)
	$(eval PROJECT_OUT=$(PROJECT_OUT)/$(SUBPROJECT_NAME))

	echo "calling makefile of subproject, name: $(SUBPROJECT_NAME)"
	echo "calling makefile of subproject, path: $(SUBPROJECT_PATH)"
	echo "calling makefile of subproject, out: $(PROJECT_OUT)"

	$(MAKE) -C $(SUBPROJECT_PATH) \
		ROOTDIR=$(ROOT_DIR) \
		PROJECTROOT=$(PROJECT_ROOT) \
		PROJECTOUT=$(PROJECT_OUT) \
		OUTDIR=$(OUT_DIR) \
		$(SUBPROJECT_TARGET)
endef

# example of calling make on multiple subdirectories
# subdirs:
# 	for dir in $(SUBDIRS); do \
# 		$(MAKE) -C $$dir; \
# 	done

all: $(APPS)
	if [ ! -d $(PROJECT_OUT) ] ; then mkdir -p $(PROJECT_OUT) ; fi

clean:
	if [ -d "$(PROJECT_OUT)" ] ; then rm -r $(PROJECT_OUT) ; fi

clean-enchatter:
	$(call subproject_make_with_common_attributes, enchatter, $(APP_DIR_ENCHATTER), clean)

enchatter: $(APP_DIR_ENCHATTER)
	$(call subproject_make_with_common_attributes, $@, $<, all)
