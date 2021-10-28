package com.ipn.datastructuredemo

import java.util.*

class Lista
{
    fun main(args: Array<String>)
    {
        val linkedList = LinkedList<String>()
        //Agregar elementos a la lista
        for (i in 0..9) linkedList.add(i.toString())

        //Eliminar un elemento por su posición
        linkedList.removeAt(5)
        //Remover y obtener el primer item de la lista
        linkedList.pop()

        //Obtener el primer item de la lista sin removerlo
        println(linkedList.peek())
        //Obtener un elemento por su posición
        println(linkedList[3])

        //Editar el 4to elemento
        linkedList[4] = "124"

        //Imprimir valores
        linkedList.forEach(System.out::println)
    }
}