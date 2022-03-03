package com.animation.javaassist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                             //Replacing Content of Method at runtime and adding a new Method in Calculator class
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("com.sample.app.Calculator");
            CtMethod ctMethod = ctClass.getDeclaredMethod("simpleMethod");
            ctMethod.setBody("{ return \"This is still a method in Calculator class but we can modify a method here \"; }");
            CtMethod addnewMethod = CtNewMethod.make("public int newlyMethod(int dx){x+=dx;}", ctClass);
            ctClass.addMethod(addnewMethod);
            ctClass.toClass();
            Calculator calculator = new Calculator();                          //reference https://www.javassist.org/tutorial/tutorial.html
            String msg = calculator.simpleMethod();
            System.out.println(msg);
        } catch (NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }

    }
}