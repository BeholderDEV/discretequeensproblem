/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.LogNormalDistribution;

/**
 *
 * @author Augustop
 */
public class DistributionController {
    private static List<Double> numerosGerados = new ArrayList<>(); 
    
    public static void logNormal(double mean, double stand, double size){
        numerosGerados.clear();
        mean = 0.0527;
        stand = 0.0401;
        double mu = Math.log(mean * mean / Math.sqrt(mean * mean + stand * stand));
        double sigma = Math.sqrt(Math.log((mean * mean + stand * stand) / (mean * mean)));
//        DecimalFormat df = new DecimalFormat("#.######");
//        df.setRoundingMode(RoundingMode.HALF_UP);
        LogNormalDistribution lg = new LogNormalDistribution(mu, sigma);
        Double n;
        for (int i = 0; i < size; i++) {
            n = lg.sample();
            numerosGerados.add(n);
        }
    }
    
    public static void exponential(){
        ExponentialDistribution ex = new ExponentialDistribution(207);
        System.out.println(ex.probability(3, 500));
    }

    public static List<Double> getNumerosGerados() {
        return numerosGerados;
    }
}
