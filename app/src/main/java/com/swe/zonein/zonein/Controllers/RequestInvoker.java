package com.swe.zonein.zonein.Controllers;

import com.swe.zonein.zonein.Models.Command;

/**
 * Created by malsa on 28/4/2016.
 */
public class RequestInvoker {
    //Let the button be invoker somehow??
    //ArrayList of commands ??
    Command command;

    public RequestInvoker(Command command){

        this.command = command;
    }

    public void undo(int id){

        command.execute(id);
    }
}
