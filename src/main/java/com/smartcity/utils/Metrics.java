package com.smartcity.utils;

public interface Metrics {
    void start();        // запуск таймера
    void stop();         // остановка таймера
    long getTimeNano();  // вернуть время в наносекундах

    void incrementOps(); // добавить одну операцию
    long getOpsCount();  // вернуть количество операций
}
