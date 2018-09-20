omnichat_root := /mnt/shared_home/home/tank/my/codes/projects/chat/enchatter/src

ECHO=@$$(which echo)

ALL_TARGETS := \
	omnichat
	# tests

# [OBSOLETE COMMENT, FIXME]
# Assign variables created by configure (all lowercase) to Make variables.
ROOT_DIR := $(omnichat_root)

OUT_DIR := $(ROOT_DIR)/out
BIN_DIR := $(OUT_DIR)/bin
LIB_DIR := $(ROOT_DIR)/lib
SRC_DIR := $(ROOT_DIR)/src
LIB_OUT_DIR := $(OUT_DIR)/lib
LIBKTLS_OUT_DIR := $(LIB_OUT_DIR)/libktls

RUST_OUT_DIR := $(ROOT_DIR)/target
# TODO
# Tell cargo to output file to bin dir instead
RUST_BIN_DIR := $(RUST_OUT_DIR)/debug

# APP_DIR := $(SRC_DIR)/app

OMNICHAT_BIN := $(BIN_DIR)/omnichat
CORE_BIN := $(RUST_BIN_DIR)/core
CLI_BIN := $(BIN_DIR)/cli
LIBKTLS := $(LIBKTLS_OUT_DIR)/libktls.a

LIBKTLS_SRC_DIR := $(SRC_DIR)/libktls
CORE_SRC_DIR := $(SRC_DIR)/core

# TODO
# Seriously should do this with submake. Then can use general rule for .c files
LIBKTLS_SRC_FILE1 := $(LIBKTLS_SRC_DIR)/fun1.c
LIBKTLS_SRC_FILE2 := $(LIBKTLS_SRC_DIR)/fun2.c
LIBKTLS_OBJ_FILE1 := $(LIBKTLS_OUT_DIR)/fun1.o
LIBKTLS_OBJ_FILE2 := $(LIBKTLS_OUT_DIR)/fun2.o

# LIBKTLS_SRC_FILES := $(wildcard $(LIBKTLS_SRC_DIR)/*.c)
LIBKTLS_OBJ_FILES := \
	$(LIBKTLS_OBJ_FILE1) \
	$(LIBKTLS_OBJ_FILE2)
CORE_SRC_FILES := $(wildcard $(CORE_SRC_DIR)/*.rs)

REPO_NAME := omnichat

# GLOBAL_ENCHATTER_LINK_PATH := /usr/local/bin/enchatter

# Make does not consider implicit rules for PHONY targets
# .PHONY: all clean testrun install uninstall clean-app clean-enchatter $(ALL_TARGETS)
.PHONY: all clean core cli libktls $(ALL_TARGETS)

define compile_no_link_from_to
	$(eval IN_PATH=$1)
	$(eval OUT_PATH=$2)

	$(CC) -o $(OUT_PATH) -c $(IN_PATH)
endef

# TODO
# Create test target. First test could check linking C with Rust
# successfully.

all: $(ALL_TARGETS)

clean:
	rm -f $(LIBKTLS)
	rm -f $(LIBKTLS_OBJ_FILES)
	rm -rf $(RUST_OUT_DIR)

omnichat: $(OMNICHAT_BIN)
	$(ECHO) Omnichat build top level still empty!

$(OMNICHAT_BIN): $(CORE_BIN) $(CLI_BIN)
	$(ECHO) omnichat_bin not implemented!

$(LIBKTLS): $(LIBKTLS_OBJ_FILES)
	$(ECHO) LIBKTLS deps: $^
	ar rvs $@ $^

$(LIBKTLS_OBJ_FILE1): $(LIBKTLS_SRC_FILE1)
	$(ECHO) file1 rule, target: $@ dep: $<
	$(call compile_no_link_from_to, $<, $@)

$(LIBKTLS_OBJ_FILE2): $(LIBKTLS_SRC_FILE2)
	$(ECHO) file2 rule, target: $@ dep: $<
	$(call compile_no_link_from_to, $<, $@)

$(CORE_BIN): $(CORE_SRC_FILES) $(LIBKTLS)
	cd $(CORE_SRC_DIR) && RUSTFLAGS="-L $(LIBKTLS_OUT_DIR)" cargo build

$(CLI_BIN):
	$(ECHO) cli rule not implemented!
