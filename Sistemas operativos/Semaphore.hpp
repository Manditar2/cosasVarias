#ifndef SEMAPHORE_HPP // Guarda de inclusión, para evitar duplicados. Si no ha sido ya incluida por el preprocesador, la define como sigue.
#define SEMAPHORE_HPP
#include<atomic>
#include<iostream>

class Semaphore
{
    private:
        std::atomic<int> count_;
    public:
        Semaphore(int count);
        void signal();
        void wait();
        bool try_wait();
};
 
#endif