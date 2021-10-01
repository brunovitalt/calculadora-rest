package com.example.calculadora;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");

        }
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
        return sum;
        }

    @RequestMapping(value="/divide/{numberOne}/{numberTwo}",method=RequestMethod.GET)
    public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");

        }
        Double divide = convertToDouble(numberOne) / convertToDouble(numberTwo);
        return divide;
    }

    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}",method=RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");

        }
        Double multiplication = convertToDouble(numberOne) * convertToDouble(numberTwo);
        return multiplication;
    }

    @RequestMapping(value="/subtraction/{numberOne}/{numberTwo}",method=RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");

        }
        Double subtraction = convertToDouble(numberOne) - convertToDouble(numberTwo);
        return subtraction;
    }

    @RequestMapping(value="/source/{numberOne}",method=RequestMethod.GET)
    public Double source(@PathVariable("numberOne") String numberOne) throws Exception{
        if(!isNumeric(numberOne)) {
            throw new UnsupportedOperationException("Please set a numeric value!");

        }
        double source = Double.parseDouble(numberOne);
        return Math.sqrt(source);
    }

    private Double convertToDouble(String srtNumber) {
        if (srtNumber == null) return 0d;
        String number = srtNumber.replaceAll( ",", ",");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0d;
    }

    public boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}


