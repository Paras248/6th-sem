#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

void main(){
    int fp1 = open("original-file.txt", O_RDONLY);
    int fp2 = open("copy-file.txt", O_WRONLY);
    char str[100];
    if(fp1 < 0){
        printf("File doesn't exists");
        exit(1);
    }
    size_t len = 0;
    len = read(fp1, str, sizeof str);
    write(fp2, str, len);
    close(fp1);
    close(fp2);
}