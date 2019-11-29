package plugin;

import javafx.beans.property.SimpleIntegerProperty;

public class SimpleRangeIntProperty extends SimpleIntegerProperty {
	private int upperBound = Integer.MAX_VALUE, lowerBound = Integer.MIN_VALUE;

	public SimpleRangeIntProperty() {
		super();
	}

	public SimpleRangeIntProperty(int initialValue) {
		super(initialValue);
	}

	public SimpleRangeIntProperty(int LowerBound, int UpperBound) {
		super();
		trySetRange(LowerBound, UpperBound);
	}

	public SimpleRangeIntProperty(int initialValue, int LowerBound, int UpperBound) {
		super(initialValue);
		trySetRange(LowerBound, UpperBound);
	}

	public void setRange(int LowerBound, int UpperBound) throws Exception {
		if (LowerBound > UpperBound)
			throw new Exception("Cận trên phải lớn hơn cạnh dưới");
		lowerBound = LowerBound;
		upperBound = UpperBound;
	}

	public void trySetRange(int LowerBound, int UpperBound) {
		try {
			setRange(LowerBound, UpperBound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void set(int newValue) {
		if (newValue < lowerBound)
			super.set(lowerBound);
		else if (newValue > upperBound)
			super.set(upperBound);
		else
			super.set(newValue);
	};

	@Override
	public void setValue(Number v) {
		set((int) v);
	}
}