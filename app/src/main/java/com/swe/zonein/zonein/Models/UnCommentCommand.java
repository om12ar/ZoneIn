package com.swe.zonein.zonein.Models;

import com.swe.zonein.zonein.Controllers.Requests;

/**
 * Created by malsa on 28/4/2016.
 */
public class UnCommentCommand implements Command {
    Requests request;
    @Override

    public void execute(int id) {

        request.unComment(id);
    }
}
