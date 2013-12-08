package algorithm;

import marshall.Marshaller;
import marshall.MemberToMelodyMarshaller;

public class MelodyMaker {

    private final GeneticAlgorithm geneticAlgorithm;
    private final Marshaller<Member, Melody> memberConverter;

    public static MelodyMaker newInstance() {
        GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.newInstance();
        MemberToMelodyMarshaller melodyMarshaller = new MemberToMelodyMarshaller();
        return new MelodyMaker(geneticAlgorithm, melodyMarshaller);
    }

    public MelodyMaker(GeneticAlgorithm geneticAlgorithm, Marshaller<Member, Melody> memberConverter) {
        this.geneticAlgorithm = geneticAlgorithm;
        this.memberConverter = memberConverter;
    }

    public Melody make() {
        Member result = geneticAlgorithm.generate();
        return memberConverter.marshall(result);
    }

}
