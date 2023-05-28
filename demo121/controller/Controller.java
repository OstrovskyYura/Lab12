package com.example.demo121.controller;

import com.example.demo121.beans.TabulationBean;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private TabulationBean tabulationBean;

    @PostMapping("tabulate")
    public String tabulate(@RequestParam double a, @RequestParam double b, @RequestParam double c, @RequestParam double start,
                           @RequestParam double end,@RequestParam double step, Model model){
        tabulationBean.fillPoints(a, b, c, start, end, step);
        int Steps = tabulationBean.steps(start, end, step);
        double [] ar1 = tabulationBean.massX(end, start, step);
        double [] ar2 = tabulationBean.massY(ar1, a, b, c);
        model.addAttribute("points", tabulationBean.getPoints());
        model.addAttribute("steps", Steps);
        model.addAttribute("maximumX", tabulationBean.getMaxX(ar2, ar1));
        model.addAttribute("minimumX", tabulationBean.getMinX(ar2, ar1));
        model.addAttribute("maximumY", tabulationBean.getMaxY(ar2));
        model.addAttribute("minimumY", tabulationBean.getMinY(ar2));
        model.addAttribute("suma", tabulationBean.sum(ar2));
        model.addAttribute("arithmetical", tabulationBean.am(ar2));
        return "tabulation";
    }
}


