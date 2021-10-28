package com.ipn.datastructuredemo

import java.util.*

class Pila
{
    fun main(args: Array<String>)
    {
        val pila: Stack<Int> = Stack()

        //Agregar elementos a la pila
        for (i in 0..9) pila.push(i)

        //Eliminar un elemento por su posición
        pila.remove(5)
        //Remover y obtener el primer item de la pila
        pila.pop()

        //Obtener el primer item de la pila sin removerlo
        println(pila.peek())
        //Obtener un elemento por su posición
        println(pila[3])

        //Imprimir valores
        pila.forEach(System.out::println)
    }
}