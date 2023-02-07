#include<stdio.h>


void main(){
    FILE *fp1, *fp2;
    fp1 = fopen("original-file.txt", "r");
    fp2 = fopen("copy-file.txt", "w");
    char str[100];
    while((fgets(str, sizeof str, fp1) != NULL)){
        fputs(str, fp2);
    };
    fclose(fp1);
    fclose(fp2);
}