@RestController
@RequestMapping("/agenteia")
public class AgenteIAController {

    @Autowired
    private AgenteIARepository agenteIARepository;

    @GetMapping
    public List<AgenteIA> listar() {
        return agenteIARepository.findAll();
    }

    @PostMapping
    public AgenteIA salvar(@RequestBody AgenteIA agenteIA) {
        return agenteIARepository.save(agenteIA);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agenteIARepository.deleteById(id);
    }
}
