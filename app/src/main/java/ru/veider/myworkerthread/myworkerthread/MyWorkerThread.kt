package ru.veider.myworkerthread.myworkerthread

class MyWorkerThread {

    private var threads: MutableMap<String, Thread> = HashMap()

    fun run(name: String, value: () -> Unit) {
        val newThread = Thread{
            value.invoke()
            if (threads.containsKey(name))
                threads.remove(name)
        }
        newThread.name = name
        threads[name] = newThread
        newThread.start()
    }

    fun checkThread(name: String): Boolean =
            if (threads.containsKey(name))
                threads[name]?.isAlive ?: false
            else false

}
