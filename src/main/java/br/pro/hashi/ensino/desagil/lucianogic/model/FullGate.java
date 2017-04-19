package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	private AndGate andgate1;
	private AndGate andgate2;
	private XorGate xorgate1;
	private XorGate xorgate2;
	private OrGate orgate;


	public FullGate() {
		super(3, 2);

		name = "FULL";

		andgate1 = new AndGate();
		andgate2 = new AndGate();
		xorgate1 = new XorGate();
		xorgate2 = new XorGate();
		orgate = new OrGate();

		xorgate2.connect(xorgate1, 0);
		andgate1.connect(xorgate1, 0);
		orgate.connect(andgate1, 0);
		orgate.connect(andgate2, 1);

	}

	@Override
	public boolean doRead(int index) {
		if(index==0){
			return xorgate2.read();
		}
		else{
			return orgate.read();
		}
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			xorgate1.connect(emitter, 0);
			andgate2.connect(emitter, 0);
			break;
		case 1:
			xorgate1.connect(emitter, 1);
			andgate2.connect(emitter, 1);
			break;
		case 2:
			andgate1.connect(emitter, 1);
			xorgate2.connect(emitter, 1);
			break;
			
		}
	}
}

