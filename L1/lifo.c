#include <stdio.h>
#include <stdlib.h>

typedef struct Queue {
    struct QueueItem* head;
} queue;

typedef struct QueueItem {
    int value;
    struct QueueItem* nextQueueItem;
} queueItem;

void createQueue(queue* q) {
    q->head = NULL;
}

queueItem* findLastElement(queueItem* head) {
    if(head == NULL) return NULL;
    while(head->nextQueueItem != NULL) {
        return findLastElement(head->nextQueueItem);
    }
    return head;
}

void addItem(queue* q, int value) {
    queueItem* newItem = malloc(sizeof(queueItem));
    newItem->value = value;
    newItem->nextQueueItem = NULL;
    queueItem* lastItem = findLastElement(q->head);
    if(lastItem == NULL) {
        q->head = newItem;
    } else {
        lastItem->nextQueueItem = newItem;
    }
    
    printf("Dodano %d\n", value);
}

void popItem(queue* q) {
    if(q->head == NULL) {
        printf("Ta kolejka nie ma juz elementow!\n");
        return;
    }
    queueItem* itemToPop = q->head;

    printf("Pobieramy wartosc %d\n", itemToPop->value);
    q->head = itemToPop->nextQueueItem;
    free(itemToPop);
}

int main() {
    struct Queue queue;
    createQueue(&queue);
    
    for(int i = 0; i < 100; i++) {
        addItem(&queue, i);
    }
    for(int i = 0; i < 101; i++) {
        popItem(&queue);
    }

    return 0;
}