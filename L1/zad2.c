#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct List {
    struct ListItem* head;
} list;

typedef struct ListItem {
    int value;
    struct ListItem* nextListItem;
} listItem;

void createList(list* l) {
    l->head = NULL;
}

int findElement(list* l, int index) {
    if(l == NULL || l->head == NULL) return -1;
    listItem* next = malloc(sizeof(listItem));
    next = l->head;
    for(int i = 0; i < index; i++) {
        if(next == NULL || next->nextListItem == NULL) return -1;
        next = next->nextListItem;
    }

    return next->value;
}

listItem* findLastElement(listItem* head) {
    if(head == NULL) return NULL;
    while(head->nextListItem != NULL) {
        return findLastElement(head->nextListItem);
    }
    return head;
}

void addItem(list* l, int value) {
    listItem* newItem = malloc(sizeof(listItem));
    newItem->value = value;
    newItem->nextListItem = NULL;
    listItem* lastItem = findLastElement(l->head);
    if(lastItem == NULL) {
        l->head = newItem;
    } else {
        lastItem->nextListItem = newItem;
    }
}

void mergeLists(list* l1, list* l2) {
    listItem* lastItemInFirstList = findLastElement(l1->head);
    lastItemInFirstList->nextListItem = l2->head;
}

int main() {
    time_t t1;
    srand((unsigned) time (&t1));

    struct List list1;
    struct List list2;
    createList(&list1);
    createList(&list2);
    
    for(int i = 0; i < 1000; i++) {
        addItem(&list1, rand());
        addItem(&list2, rand());
    }

    clock_t start, end;

    printf("Pomiar dla ostatniego elementu z listy: (w mikrosekundach)\n");
    start = clock();
    findElement(&list1, 999);
    end = clock();
    printf("Wyszlo: %.5f\n", ((double)(end-start)));

    printf("Pomiar dla pierwszego elementu z listy: (w mikrosekundach)\n");
    start = clock();
    findElement(&list1, 1);
    end = clock();
    printf("Wyszlo: %.5f\n", ((double)(end-start)));

    printf("Pomiar dla losowego elementu z listy: (w mikrosekundach)\n");
    start = clock();
    findElement(&list1, rand() % 1000);
    end = clock();
    printf("Wyszlo: %.5f\n", ((double)(end-start)));


    printf("Niektore elementy przed polaczeniem list:\n");
    printf("Element at 10 = %d\n", findElement(&list1, 10));
    printf("Element at 80 = %d\n", findElement(&list1, 80));
    printf("Element at 1002 = %d\n", findElement(&list1, 1002));
    mergeLists(&list1, &list2);
    printf("Te same elementy po polaczeniu list:\n");
    printf("Element at 10 = %d\n", findElement(&list1, 10));
    printf("Element at 80 = %d\n", findElement(&list1, 80));
    printf("Element at 1002 = %d\n", findElement(&list1, 1002));

    return 0;
}