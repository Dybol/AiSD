#include <stdio.h>
#include <stdlib.h>

typedef struct Queue {
    struct QueueItem* head;
} queue;

typedef struct QueueItem {
    int value;
    struct QueueItem* prevQueueItem;
} queueItem;

void createQueue(queue* q) {
    q->head = NULL;
}

void addItem(queue* q, int value) {
    queueItem* newItem = malloc(sizeof(queueItem));
    newItem->value = value;
    if(q->head == NULL) {
        newItem->prevQueueItem = NULL;
    } else {
        newItem->prevQueueItem = q->head;
    }
    q->head = newItem;
    printf("Dodano %d\n", value);
}

void popItem(queue* q) {
    if(q->head == NULL) {
        printf("Ta kolejka nie ma juz elementow!\n");
        return;
    }
    queueItem* itemToPop = q->head;

    printf("Pobieramy wartosc %d\n", itemToPop->value);
    q->head = itemToPop->prevQueueItem;
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