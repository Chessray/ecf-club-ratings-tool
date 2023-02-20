package uk.co.kleindelao.ecf.clubratings.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
open class WicketApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplicationBuilder().sources(WicketApplication::class.java).run(*args)
        }
    }
}