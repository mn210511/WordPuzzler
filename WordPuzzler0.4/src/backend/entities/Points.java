package backend.entities;

public enum Points {

	
E(1), T(1),
N(1), S(1),
U(1), A(1),
I(1), R(1),
D(1), H(2),
G(2), L(2), 
O(2), M(3), 
B(3), W(3), 
Z(3), C(4), 
F(4), K(4), 
P(4), Ö(6), 
J(6), Ä(6), 
V(6), Ü(8), 
X(8), Q(10), 
Y(10);
	 


final private int value;

private Points(int nr) {
	this.value = nr;
}

public int getValue() {
	return value;
}



}
