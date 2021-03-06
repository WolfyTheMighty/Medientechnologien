import java.io.IOException;

public class wave_io 
{
	public static void main(String[] args) 
	{
		int samples=0;
		int validBits=0;
		long sampleRate=0;
		long numFrames=0; 
		int numChannels=0;

		String inFilename = null;
		String outFilename = null;
		
		if (args.length < 1) {
			try { throw new WavFileException("At least one filename specified  (" + args.length + ")"); }
			catch (WavFileException e1) { e1.printStackTrace(); }
		}
	
		// Samples in dem Array readWavFile.sound
		
		inFilename=args[0];
		
		// Implementierung bei einem Eingabeparameter

	     WavFile readWavFile = null;
		try {
			readWavFile = WavFile.read_wav(inFilename);
			
			// headerangaben
			numFrames = readWavFile.getNumFrames(); 
			numChannels = readWavFile.getNumChannels();
			samples = (int)numFrames*numChannels;
			validBits = readWavFile.getValidBits();
			sampleRate = readWavFile.getSampleRate();
		
			// 2a Samples schreiben	
			for (int i=0; i < samples;i++) {

				System.out.println(readWavFile.sound[i]);
				
			}
		    
		    if (args.length == 1) 
				System.exit(0);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WavFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		// Implementierung bei Ein-und Ausgabeparameter
		
		outFilename=args[1];
		try {

//			 2e Downsampling

				numFrames /=2;
				sampleRate /= 2;
				for (int i=0; i < samples/2;i++) {
					System.out.println(samples);
					readWavFile.sound[i] = readWavFile.sound[i*2];

			}
			
 			// 3b Bitreduzierung
			int reduced_bits = 12;
				for (int i=0; i < samples;i++) {
					readWavFile.sound[i]/= Math.pow(2, reduced_bits);
					readWavFile.sound[i]*= Math.pow(2, reduced_bits);


			}
			
 			// 3e Bitreduzierung Differenz
			reduced_bits = 1;
			for (int i=0; i < samples;i++) {

				//
				
			}
			
			WavFile.write_wav(outFilename, numChannels, numFrames, validBits, sampleRate, readWavFile.sound);
		}			
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
