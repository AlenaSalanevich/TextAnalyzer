/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.analyzer;

import java.util.Stack;

/**
 *
 * @author Admin
 */
public class BracketsValidator {
 
private Stack<Character> stack = new Stack<Character>();
    
    private boolean isOpeningBracket(char bracket) {
        return "({[".indexOf(bracket) != -1; // проверяем скобку на принадлежность к открытым  
    }
    
    private boolean isClosingBracket(char bracket) {
        return ")}]".indexOf(bracket) != -1; // проверяем скобку на принадлежность к  закрытым
    }
 
    private boolean isPair(char opening, char closing) {
        return opening == '(' && closing == ')' || opening == '['  // проверяем "парность скобок"
                && closing == ']' || opening == '{' && closing == '}';
    }
 
    public boolean validate(String input) {
        for (char c : input.toCharArray()) {  // преобразуем строку в массив символов и в цикле по массиву:
            if (isClosingBracket(c) && stack.isEmpty()) //если первая встречающаяся скобка из "закрывающихся", и одновременно стек пуст
            {
                return false;
            }
            if (isOpeningBracket(c)) //если первая встречающаяся скобка из "открывающихся", 
            {
                stack.push(c); //то записываем ее в стек
            }
            if (isClosingBracket(c)) //если первая встречающаяся скобка из "закрывающихся", а стек то уже не пуст
            {
                if (isPair(stack.peek(), c)) { // считываем из стека (не удаляя) и проверяем на "парность" 
                    stack.pop(); // если оба символа парные, то очищаем стек
                }
            }
        }
        return stack.isEmpty(); // если по окончании цикла стек пустой, то наши скобки парные, все клево))
    }
}
