#include <stdio.h>

extern int fun1(void);
extern int fun2(void);

int main(void) {

	printf("fun1 returns: %d\n", fun1());
	printf("fun2 returns: %d\n", fun2());

	return 0;
}
