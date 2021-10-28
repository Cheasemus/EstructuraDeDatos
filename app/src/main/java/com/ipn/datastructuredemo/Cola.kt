package com.ipn.datastructuredemo

import java.util.*

class Cola
{
    fun main(args: Array<String>)
    {
        val queue: Queue<String> = LinkedList()
        for (i in 0..9) queue.add(i.toString())

        //Eliminar el primer elemento de la cola
        queue.remove()

        //Obtener el primer item de la cola sin removerlo
        println(queue.element())

        //Imprimir valores
        queue.forEach(System.out::println)
    }
}