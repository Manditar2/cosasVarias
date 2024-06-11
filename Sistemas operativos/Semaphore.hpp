#include<atomic>
#include<iostream>
#ifndef SEMAPHORE_H // Guarda de inclusión, para evitar duplicados. Si no ha sido ya incluida por el preprocesador, la define como sigue.
#define SEMAPHORE_H

class Semaphore
{
    private:
        std::atomic<int> count_;
    public:
        Semaphore() : count_(0){};
        void signal();
        void wait();
        bool try_wait();
};
 
#endif