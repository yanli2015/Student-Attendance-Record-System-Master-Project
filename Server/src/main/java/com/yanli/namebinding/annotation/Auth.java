package com.yanli.namebinding.annotation;

import java.lang.annotation.*;
import java.lang.annotation.Retention;

import javax.ws.rs.NameBinding;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

}

