package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {

	private XorGate xorgate;
	private AndGate andgate;


	public HalfGate() {
		super(2, 2);

		name = "HALF";

		xorgate = new XorGate();

		andgate = new AndGate();

	}

	@Override
	public boolean doRead(int index) {
		if(index == 0){
			return xorgate.read();
		}
		else{
			return andgate.read();
		}
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			xorgate.connect(emitter, 0);
			andgate.connect(emitter, 0);
			break;
		case 1:
			xorgate.connect(emitter, 1);
			andgate.connect(emitter, 1);
			break;
		}
	}
}