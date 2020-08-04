package org.example.koin.hello

import org.koin.core.qualifier.named
import org.koin.dsl.module

val helloModule = module {
    single(named("foo")) { Greeting("Mr. Foo") }
    single { Greeting("John Doe") }
    single { HelloServiceImpl(get(named("foo"))) as HelloService }
}
