package ru.veider.myworkerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ru.veider.myworkerthread.myworkerthread.MyWorkerThread

class MainActivity : AppCompatActivity() {

    private val THREAD_1 = "THREAD_1"
    private val THREAD_2 = "THREAD_2"

    private val myWorkerThread by lazy { MyWorkerThread() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView1 = findViewById<TextView>(R.id.text_view_1)
        findViewById<Button>(R.id.button_test_1_start).apply {
            setOnClickListener {
                myWorkerThread.run(THREAD_1) {
                    val maxNumber = 100
                    var sum: Long = 0
                    for (i in 1..maxNumber) {
                        if (i % 2 == 0) sum += i
                        Handler(Looper.getMainLooper()).post {
                            textView1.text = "Сумма чётных чисел в диапазоне 0..$i = $sum"
                        }
                        Thread.sleep(101)
                    }
                }
            }
        }
        findViewById<Button>(R.id.button_test_1_stop).apply {
            setOnClickListener {
                Toast.makeText(this@MainActivity, if (myWorkerThread.checkThread(THREAD_1)) "Поток 1 запущен" else "Поток 1 отработал",
                               Toast.LENGTH_SHORT
                ).show()
            }
        }

        val textView2 = findViewById<TextView>(R.id.text_view_2)
        findViewById<Button>(R.id.button_test_2_start).apply {
            setOnClickListener {
                myWorkerThread.run(THREAD_2) {
                    val maxNumber = 100
                    var sum: Long = 0
                    for (i in 1..maxNumber) {
                        if (i % 2 != 0) sum += i
                        Handler(Looper.getMainLooper()).post {
                            textView2.text = "Сумма нечётных чисел в диапазоне 0..$i = $sum"
                        }
                        Thread.sleep(100)
                    }
                }
            }
        }
        findViewById<Button>(R.id.button_test_2_stop).apply {
            setOnClickListener {
                Toast.makeText(this@MainActivity, if (myWorkerThread.checkThread(THREAD_2)) "Поток 2 запущен" else "Поток 2 отработал",
                               Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}