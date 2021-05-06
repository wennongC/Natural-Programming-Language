/** Non-Deterministic Finite Automaton **/
package NPLang.parser;

import NPLang.ast.basic.ASTree;

import java.util.ArrayList;
import java.util.HashMap;

public class NDFA {
    private static String UNIVERSAL = "UNIVERSAL";
    private HashMap<String, String[]> types;   // Reserved words for specific usage
    private ArrayList<Transition> transitions; // Automaton state transitions
    private ArrayList<Integer> states;
    private ArrayList<Integer> goalStates;

    public NDFA() {
        transitions = new ArrayList<>();
        types = new HashMap<>();
    }

    public void addNewType(String typeName, String[] typeValues) {
        types.put(typeName, typeValues);
    }

    public Rule rule(Class<? extends ASTree> name) {
        Rule rule = new Rule(name, transitions);
        return rule;
    }

    // Start executing the N-DFA
    public void run(String[] inputs) {
        /**
         *          To-do
        **/
        // Need check goal states and rules
        int current = 0; // Set 0 as the initialized state

        for(String i: inputs) {
            Transition t = transitions.get(current);
        }
    }

    protected static class Transition {
        public HashMap<String, Integer> outgoings;
        public Transition() { outgoings = new HashMap<>(); }
    }

    public static class Rule {
        private Class<? extends ASTree> rule_name;
        private ArrayList<Transition> all_trans;
        private int current_state;
        public Rule(Class<? extends ASTree> n, ArrayList<Transition> t) {
            rule_name = n;
            all_trans = t;
            current_state = 0;
        }
        public Rule ast(Rule r) {
            return this;
        }
        public Rule sep() {
            return this;
        }
        public Rule or() {
            return this;
        }
        public Rule optional() {
            return this;
        }
        public Rule repeat() {
            return this;
        }
    }

}
