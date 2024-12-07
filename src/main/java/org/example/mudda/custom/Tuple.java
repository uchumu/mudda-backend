package org.example.mudda.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tuple<X, Y> {
    private X x;
    private Y y;

}
