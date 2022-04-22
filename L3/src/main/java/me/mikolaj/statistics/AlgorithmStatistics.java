package me.mikolaj.statistics;

public class AlgorithmStatistics {
	private Integer keyComparison = 0;
	private Integer keySwap = 0;

	public AlgorithmStatistics() {}

	public AlgorithmStatistics(Integer keyComparison, Integer keySwap) {
		this.keyComparison = keyComparison;
		this.keySwap = keySwap;
	}

	public Integer getKeyComparison() {
		return keyComparison;
	}

	public Integer getKeySwap() {
		return keySwap;
	}

	public void setKeyComparison(Integer keyComparison) {
		this.keyComparison = keyComparison;
	}

	public void setKeySwap(Integer keySwap) {
		this.keySwap = keySwap;
	}

	public void increaseKeyComparison() {
		keyComparison++;
	}

	public void increaseKeySwap() {
		keySwap++;
	}

	public void reset() {
		keyComparison = 0;
		keySwap = 0;
	}

	@Override
	public String toString() {
		return "AlgorithmStatistics{" +
				"keyComparison=" + keyComparison +
				", keySwap=" + keySwap +
				'}';
	}
}
