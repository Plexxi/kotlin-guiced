package org.jlleitschuh.guice

import com.google.inject.Binder
import com.google.inject.Key
import com.google.inject.TypeLiteral
import org.jlleitschuh.guice.binder.AnnotatedBindingBuilderScope
import org.jlleitschuh.guice.binder.LinkedBindingBuilderScope
import kotlin.reflect.KClass

open class BinderScope
internal constructor(private val binder: Binder) : Binder by binder {

    /**
     * Convenience.
     */
    fun binder() = this

    inline fun <reified T: Any> bind(): AnnotatedBindingBuilderScope<T> =
        bind(typeLiteral<T>())

    /**
     * See the EDSL examples at [Binder].
     */
    fun <T : Any> bind(type: KClass<T>): AnnotatedBindingBuilderScope<T> =
        bind(type.java)

    /**
     * See the EDSL examples at [Binder].
     */
    override fun <T : Any> bind(key: Key<T>): LinkedBindingBuilderScope<T> =
        LinkedBindingBuilderScope(binder.bind(key))

    /**
     * See the EDSL examples at [Binder].
     */
    override fun <T : Any> bind(type: Class<T>): AnnotatedBindingBuilderScope<T> =
        AnnotatedBindingBuilderScope(binder.bind(type))

    /**
     * See the EDSL examples at [Binder].
     */
    override fun <T : Any> bind(typeLiteral: TypeLiteral<T>): AnnotatedBindingBuilderScope<T> =
        AnnotatedBindingBuilderScope(binder.bind(typeLiteral))


    override fun newPrivateBinder(): PrivateBinderScope =
        PrivateBinderScope(binder.newPrivateBinder())

}