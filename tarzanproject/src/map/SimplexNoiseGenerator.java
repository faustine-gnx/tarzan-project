package map;

import java.util.Random;

public class SimplexNoiseGenerator implements MapGenerator {
	int OCTAVES;
	double ROUGHNESS;
	double SCALE; 

	public SimplexNoiseGenerator(int octaves, double roughness, double scale) {
		this.OCTAVES = octaves; // Number of Layers combined together to get a natural looking surface
		this.ROUGHNESS = roughness; // Increasing the of the range between -1 and 1, causing higher values eg more
		// rough terrain
		this.SCALE = scale; // Overall scaling of the terrain
	}


	private float[][] generateOctavedSimplexNoise(int size) {
		float[][] totalNoise = new float[size][size];
		double layerFrequency = SCALE;
		double layerWeight = 1;

		Random r = new Random();

		SimplexNoise.setSeed(r.nextInt(Integer.MAX_VALUE));

		for (int octave = 0; octave < OCTAVES; octave++) {
			// Calculate single layer/octave of simplex noise, then add it to total noise
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					totalNoise[x][y] += SimplexNoise.noise(x * layerFrequency, y * layerFrequency) * layerWeight;
				}
			}

			// Increase variables with each incrementing octave
			layerFrequency *= 2;
			layerWeight *= ROUGHNESS;

		}
		return totalNoise;
	}

	@Override
	public float[][] createMap(int size) {
		return generateOctavedSimplexNoise(size);
	}
}
