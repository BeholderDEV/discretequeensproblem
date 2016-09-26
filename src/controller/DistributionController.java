/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    public static void logNormal(double mean, double stand, double size, double extra){
        numerosGerados.clear();
        double mu = Math.log(mean * mean / Math.sqrt(mean * mean + stand * stand));
        double sigma = Math.sqrt(Math.log((mean * mean + stand * stand) / (mean * mean)));
        LogNormalDistribution lg = new LogNormalDistribution(mu, sigma);
        Double n;
        for (int i = 0; i < size; i++) {
            n = extra + lg.sample();
            numerosGerados.add(n);
        }
    }
    
    public static void exponential(double media, double extra, int size){
        numerosGerados.clear();
        ExponentialDistribution ex = new ExponentialDistribution(media);
        for (int i = 0; i < size; i++) {
            numerosGerados.add(extra + ex.sample());
        }
    }

    public static List<Double> getNumerosGerados() {
        return numerosGerados;
    }
}
