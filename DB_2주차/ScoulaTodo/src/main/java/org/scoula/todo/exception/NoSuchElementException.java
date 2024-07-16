package org.scoula.todo.exception;

public class NoSuchElementException extends Exception {
    public NoSuchElementException(){
        super("일치하는 내용이 없습니다.");
    }
}
