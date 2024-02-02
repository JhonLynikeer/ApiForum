package br.com.alura.demo.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
