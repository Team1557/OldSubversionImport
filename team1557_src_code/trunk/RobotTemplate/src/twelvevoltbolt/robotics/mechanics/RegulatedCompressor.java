package twelvevoltbolt.robotics.mechanics;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class RegulatedCompressor {
    private Relay compressor;
    private DigitalInput shutoff;
    
    public RegulatedCompressor(Relay compressor, DigitalInput shutoff) {
        this.compressor = compressor;
        this.shutoff = shutoff;
    }
    
    /**
     * Shuts off the compressor if the shutoff DigitalInput is sending an on signal, otherwise turns the compressor on.
     */
    public void regulate() {
        getCompressor().set(getShutoff().get() ? Relay.Value.kOff : Relay.Value.kForward);
    }
    
    public Relay getCompressor() {
        return compressor;
    }

    public void setCompressor(Relay compressor) {
        this.compressor = compressor;
    }

    public DigitalInput getShutoff() {
        return shutoff;
    }

    public void setShutoff(DigitalInput shutoff) {
        this.shutoff = shutoff;
    }
    
    public static RegulatedCompressor fromIds(int compressorRelayId, int shutoffDigitalInputId) {
        return new RegulatedCompressor(new Relay(compressorRelayId), new DigitalInput(shutoffDigitalInputId));
    }
}
