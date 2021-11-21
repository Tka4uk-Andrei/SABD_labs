package sabd.obfuscation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import sabd.obfuscation.xml.Employee;
import sabd.obfuscation.xml.Employees;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static sabd.obfuscation.ObfuscationConstants.*;
import static sabd.obfuscation.FileReader.readFromFile;

public class Deobfuscation {

    public void deobfuscation() {
        deobfuscation(OBFUSCATED_FILE, DEOBFUSCATED_FILE);
    }

    public void deobfuscation(String fromFile, String toFile) {
        List<String> lines = readFromFile(fromFile);
        Employees employees = new Employees();
        try {
            employees = parseObfuscatedData(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        serializeToXML(employees, toFile);
    }

    private Employees parseObfuscatedData(List<String> lines) throws Exception {
        int employeeAmount = 0;
        // create map where
        // key - sign (first element in line)
        // value - sign values
        Map<String, String[]> obfuscatedData = new HashMap<>();
        for (String line : lines) {
            String[] items = line.split("\\.");
            if (employeeAmount == 0) {
                employeeAmount = items.length - 1;
            } else if (employeeAmount != items.length - 1) {
                throw new Exception("Number of elements in lines are not the same");
            }
            obfuscatedData.put(decodeString(items[0]), Arrays.copyOfRange(items, 1, items.length));
        }

        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employeeAmount; i++) {
            Employee employee = new Employee();
            for (String key : Employee.getValues()) {
                String decodedValue = decodeString(obfuscatedData.get(key)[i]);
                employee.setValue(key, decodedValue);
            }
            employeeList.add(employee);
        }

        return new Employees(employeeList);
    }

    private void serializeToXML(Employees employees, String file) {
        try {
            ObjectMapper xmlMapper = new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);

            // serialize our Object into XML string
            String xmlString = xmlMapper.writeValueAsString(employees);

            // write XML string to file
            File xmlOutput = new File(file);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error in serializing XML");
            e.printStackTrace();
        }
    }

    private String decodeString(String s) {
        return s.chars()
                .mapToObj(this::decodeChar)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    private char decodeChar(int c) {
        int index = TARGET.indexOf(c);
        return SOURCE.charAt(index);
    }
}
